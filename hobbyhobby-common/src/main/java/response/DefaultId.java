package response;

public record DefaultId(long id) {
    public static DefaultId of(long id){
        return new DefaultId(id);
    }
}
