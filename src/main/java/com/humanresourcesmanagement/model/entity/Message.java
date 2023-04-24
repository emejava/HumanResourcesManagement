package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor

@Entity(name = "messageEntity")
@Table(name = "tb_message")
@NamedQueries({
        @NamedQuery(name = "message.findAllActive",
                query = "SELECT m FROM messageEntity m WHERE m.status=:status"),
        @NamedQuery(name = "message.findBySenderAndReceiver",
                query = "SELECT m FROM messageEntity m WHERE m.sender=:sender AND m.receiver=:receiver AND m.status=:status"),
        @NamedQuery(name = "message.findAllBySender",
                query = "SELECT m FROM messageEntity m WHERE m.sender=:sender AND m.status=:status"),
        @NamedQuery(name = "message.findAllByReceiver",
                query = "SELECT m FROM messageEntity m WHERE m.receiver=:receiver AND m.status=:status")})
public class Message {
    @Id
    @JsonProperty("کد")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("موضوع")
    @NonNull
    @Column(name = "subject",columnDefinition = "NVARCHAR2(30)")
    private String subject;

    @JsonProperty("فرستنده")
    @NonNull
    @OneToOne
    private User sender;

    @JsonProperty("گیرنده")
    @NonNull
    @OneToOne
    private User receiver;

    @JsonProperty("رونوشت")
    @NonNull
    @Column(name = "receiver_role",columnDefinition = "NVARCHAR2(20)")
    private String receiverRole;

    @JsonProperty("پیام")
    @NonNull
    @Column(name = "message",columnDefinition = "NVARCHAR2(1000)")
    private String msg;

    @JsonProperty("پیوست ها")
    @OneToMany
    private List<MessageFile> messageFiles;

    @JsonProperty("زمان ارسال")
    private Timestamp dateTime;

    @JsonProperty("فوروارد شده")
    private boolean forwarded;

    @JsonProperty("فوروارد شده به")
    @OneToOne
    private User forwardedTo;

    @JsonProperty("پاسخ به")
    @OneToOne
    private Message replyTo;

    @JsonProperty("وضعیت")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "NVARCHAR2(10)")
    private Status status;

    @PrePersist
    public void receiverRoleAndDateTime() {
        receiverRole = receiver.getRole().toString();
        dateTime = Timestamp.valueOf(LocalDateTime.now());
        status = Status.Active;
    }

    public Message(
            Long id,
            @NonNull String subject,
            @NonNull User sender,
            @NonNull User receiver,
            @NonNull String receiverRole,
            @NonNull String msg,
            boolean forwarded,
            User forwardedTo) {
        this.id = id;
        this.subject = subject;
        this.sender = sender;
        this.receiver = receiver;
        this.receiverRole = receiverRole;
        this.msg = msg;
        this.forwarded = forwarded;
        this.forwardedTo = forwardedTo;
    }

    public Message(
            @NonNull String subject,
            @NonNull User sender,
            @NonNull User receiver,
            @NonNull String receiverRole,
            @NonNull String msg,
            List<MessageFile> messageFiles) {
        this.subject = subject;
        this.sender = sender;
        this.receiver = receiver;
        this.receiverRole = receiverRole;
        this.msg = msg;
        this.messageFiles = messageFiles;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


}
