
package com.icesi.backend.segurity;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.backend.error.exception.EShopError;
import com.icesi.backend.error.exception.EShopException;
import com.icesi.backend.errorConstants.BackendApplicationErrors;
import com.icesi.backend.models.PermissionUser;
import com.icesi.backend.service.LoginServiceInterface;
import com.icesi.backend.service.impl.Token_Parser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
@Order(1)
/**
 * Filtro de autorización de token.
 * Este filtro se ejecuta una vez por cada solicitud entrante y se encarga de verificar
 * la presencia y validez de un token de autorización en la solicitud.
 */
public class AuthorizationTokenFilter extends OncePerRequestFilter {

    // Constantes para los encabezados y prefijos del token de autorización
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    // Nombres de las claves en los claims del token
    private static final String USER_ID_CLAIM_NAME = "userId";
    private static final String ROLE_ID_CLAIM_NAME = "roleId";

    // Rutas excluidas de la autorización
    private static final String[] excludedPaths = {
            "POST /users",
            "POST /login",
            "OPTIONS /users",
            "OPTIONS /login",
            "OPTIONS /items",
            "OPTIONS /orders"
    };

    private final LoginServiceInterface loginService;


    /**
     * Método principal que se ejecuta en cada solicitud entrante.
     * Verifica la presencia y validez del token de autorización y realiza las acciones
     * correspondientes de filtrado y procesamiento de la solicitud.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if (containsToken(request)) {
                String jwtToken = request.getHeader(AUTHORIZATION_HEADER).replace(TOKEN_PREFIX, StringUtils.EMPTY);
                Claims claims = Token_Parser.decodeJWT(jwtToken);
                SecurityContext context = parseClaims(jwtToken, claims);
                SecurityContextHolder.setUserContext(context);
                roleFilter(context, request, response);
                filterChain.doFilter(request, response);
            } else {
                createUnauthorizedFilter(
                        new EShopException(HttpStatus.UNAUTHORIZED,
                                new EShopError(BackendApplicationErrors.CODE_L_03, BackendApplicationErrors.CODE_L_03.getMessage())),
                        response);
            }
        } catch (JwtException e) {
            System.out.println("Error verifying JWT token: " + e.getMessage());
            createUnauthorizedFilter(
                    new EShopException(HttpStatus.UNAUTHORIZED,
                            new EShopError(BackendApplicationErrors.CODE_L_03, BackendApplicationErrors.CODE_L_03.getMessage())),
                    response);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    /**
     * Filtra los permisos de acuerdo al contexto de seguridad y la solicitud HTTP.
     * Si el contexto de seguridad no tiene permisos válidos para la solicitud, se crea una respuesta de error no autorizada.
     *
     * @param context  El contexto de seguridad que contiene el ID del rol.
     * @param request  La solicitud HTTP que se está procesando.
     * @param response La respuesta HTTP donde se enviará la respuesta de error en caso de no estar autorizado.
     */
    private void roleFilter(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        List<PermissionUser> permissions = loginService.getPermissionsByRoleId(context.getRoleId());

        boolean isValid = checkPermissions(permissions, request);

        if (!isValid) {
            EShopException unauthorizedException = createUnauthorizedException();
            createUnauthorizedFilter(unauthorizedException, response);
        }
    }

    /**
     * Verifica si la lista de permisos contiene un permiso válido para la solicitud.
     *
     * @param permissions La lista de permisos que se deben verificar.
     * @param request     La solicitud HTTP que se está procesando.
     * @return {@code true} si se encuentra un permiso válido, {@code false} en caso contrario.
     */
    private boolean checkPermissions(List<PermissionUser> permissions, HttpServletRequest request) {
        return permissions.stream()
                .anyMatch(p -> p.getMethod().equals(request.getMethod()) && request.getRequestURI().startsWith(p.getUri()));
    }

    /**
     * Crea una instancia de E_SHOP_Exception con los parámetros adecuados para representar una respuesta de error no autorizada.
     *
     * @return Una instancia de E_SHOP_Exception con el código de error y mensaje correspondientes.
     */
    private EShopException createUnauthorizedException() {
        EShopError error = new EShopError(BackendApplicationErrors.CODE_L_03, BackendApplicationErrors.CODE_L_03.getMessage());
        return new EShopException(HttpStatus.UNAUTHORIZED, error);
    }





    /**
     * Parsea los claims del token y los asigna a un objeto SecurityContext.
     *
     * @param jwtToken token de autorización JWT
     * @param claims   claims extraídos del token
     * @return objeto SecurityContext con la información del usuario y el token
     * @throws MalformedJwtException si ocurre un error al parsear el JWT
     */
    private SecurityContext parseClaims(String jwtToken, Claims claims) {
        String userId = claimKey(claims, USER_ID_CLAIM_NAME);
        String roleId = claimKey(claims, ROLE_ID_CLAIM_NAME);

        SecurityContext context = new SecurityContext();
        try {
            context.setUserId(UUID.fromString(userId));
            context.setRoleId(UUID.fromString(roleId));
            context.setToken(jwtToken);
        } catch (IllegalArgumentException e) {
            throw new MalformedJwtException("Error parsing JWT");
        }
        return context;
    }

    /**
     * Obtiene el valor de una clave de los claims del token.
     * Si el valor es nulo, lanza una excepción.
     *
     * @param claims claims extraídos del token
     * @param key    clave a buscar
     * @return valor de la clave
     * @throws NullPointerException si el valor es nulo
     */
    private String claimKey(Claims claims, String key) {
        String value = (String) claims.get(key);
        return Optional.ofNullable(value).orElseThrow(NullPointerException::new);
    }

    /**
     * Determina si la solicitud contiene un token de autorización válido.
     *
     * @param request solicitud HTTP entrante
     * @return true si contiene un token de autorización válido, false en caso contrario
     */
    private boolean containsToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(AUTHORIZATION_HEADER);
        return authenticationHeader != null && authenticationHeader.startsWith(TOKEN_PREFIX);
    }

    /**
     * Determina si el filtro debe aplicarse o no a una solicitud específica.
     * Comprueba si la ruta de la solicitud está excluida de la autorización.
     *
     * @param request solicitud HTTP entrante
     * @return true si el filtro no debe aplicarse, false en caso contrario
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String methodPlusPath = request.getMethod() + " " + request.getRequestURI();
        return Arrays.stream(excludedPaths).anyMatch(path -> path.equalsIgnoreCase(methodPlusPath));
    }

    /**
     * Crea una respuesta de error de autorización.
     * Escribe el mensaje de error en la respuesta HTTP con el código de estado 401 (Unauthorized).
     *
     * @param ESHOP_Exception excepción de la aplicación
     * @param response        respuesta HTTP saliente
     * @throws IOException si ocurre un error al escribir la respuesta
     */
    @SneakyThrows
    private void createUnauthorizedFilter(EShopException ESHOP_Exception, HttpServletResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        EShopError ESHOP_Error = ESHOP_Exception.getError();
        String message = objectMapper.writeValueAsString(ESHOP_Error);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(message);
        response.getWriter().flush();
    }
}
