package service;

public enum Status {
    Add("Add"),
    Update("Update");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
