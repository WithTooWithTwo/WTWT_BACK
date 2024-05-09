package wtwt.common.util;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import wtwt.exception.dto.ErrorResponse;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void sendErrorResponse(HttpStatus httpStatus, String message,
        HttpServletResponse response) {
        sendResponse(httpStatus, ErrorResponse.of(httpStatus, message), response);
    }

    public static void sendResponse(HttpStatus httpStatus, Object responseBody,
        HttpServletResponse response) {
        try {
            setResponseContentTypeAndEncoding(response);
            response.setStatus(httpStatus.value());
            response.getWriter().write(objectMapper.writeValueAsString(responseBody));
        } catch (IOException e) {
            log.error("JSON 작성 중 오류가 발생했습니다.", e);
            sendInternalServerError(response);
        }
    }

    private static void sendInternalServerError(HttpServletResponse response) {
        try {
            ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,
                "서버 내부 에러가 발생했습니다.");

            setResponseContentTypeAndEncoding(response);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        } catch (IOException e) {
            log.error("에러 응답 전송 중 에러가 발생했습니다.", e);
        }
    }

    private static void setResponseContentTypeAndEncoding(HttpServletResponse response) {
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(UTF_8.name());
    }
}
