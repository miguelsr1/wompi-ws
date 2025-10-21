package sv.com.jsoft.microsoft.payload;

public record TokenResponse(
        String token_type,
        int expires_in,
        int exp_expires_in,
        String access_token
) {
}
