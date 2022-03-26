package com.termii.visitorslog.domain;

import com.termii.visitorslog.constants.AppConstants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "visits")
@NoArgsConstructor
@AllArgsConstructor
public class Visits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitors visitorId;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staffId;

    @NotNull
    @Column(name = "date_of_visit")
    @PastOrPresent(message = AppConstants.FUTURE_VISIT_DATE_NOT_ALLOWED_HERE)
    private LocalDateTime dateOfVisit;

    @Column(name = "reason_for_visit")
    @NotEmpty(message = AppConstants.REASON_FOR_VISIT_IS_MANDATORY)
    private String reasonForVisit;

}