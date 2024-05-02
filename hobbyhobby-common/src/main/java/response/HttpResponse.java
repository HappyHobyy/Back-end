package response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "HTTP Response")
public record HttpResponse<T>(
        @Schema(description = "HTTP status code", example = "200")
        int code,
        @Schema(description = "Message describing the response status", example = "Success")
        String message,
        @Schema(description = "Data associated with the response")
        T data
) {
  public static <T> HttpResponse<T> success(T data) {
    return new HttpResponse<>(200, "Success", data);
  }

  public static <T> HttpResponse<T> successOnly() {
    return new HttpResponse<>(200, "Success", null);
  }
}
