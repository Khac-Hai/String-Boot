package baitap.ss2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Task {
    private Integer id;
    private String title;
    private String description;
    private String priority;
    private int assignedTo; // userId
}
