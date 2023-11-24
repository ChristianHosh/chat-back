package com.chat.chatback.chat.model;

import com.chat.chatback.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_chat_message")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "chat_channel_id", nullable = false)
    private ChatChannel chatChannel;

    @Column(name = "time_sent", nullable = false)
    private Date timeSent;

    @Column(name = "content", length = 500)
    private String content;

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", sender=" + sender +
                ", chatChannel=" + chatChannel +
                ", timeSent=" + timeSent +
                ", content='" + content + '\'' +
                '}';
    }
}