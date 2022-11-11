package tpi.backend.JWT;

public class JTWResponse {
    private String token;

    public JTWResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
