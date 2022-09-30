package com.codathon.blue_eMatket_api.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Feedback extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackId;
    private String feedbackStatement;
    private String sentBy;
    private int status;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "feedbackTypeId", referencedColumnName = "feedbackTypeId")
    private FeedbackTypes feedbackTypes;
}
