package baitap.ss3.model;

public class Course {
    private Long id;
    private String title;
    private String status;
    private Long instructorId;

    public Course() {
    }

    public Course(Long id, String title, String status, Long instructorId) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.instructorId = instructorId;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Long getInstructorId() {
        return instructorId;
    }
    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", instructorId=" + instructorId +
                '}';
    }
}
