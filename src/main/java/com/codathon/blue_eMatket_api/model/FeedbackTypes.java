package com.codathon.blue_eMatket_api.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class FeedbackTypes extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackTypeId;
    private String feedbackTypeName;
    private String description;
    private int status;

    @OneToMany(mappedBy = "feedbackTypes",fetch = FetchType.LAZY)
    private List<Feedback> feedbacks;
}
