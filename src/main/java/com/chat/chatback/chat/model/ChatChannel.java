package com.chat.chatback.chat.model;

import com.chat.chatback.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "chat_channel", uniqueConstraints = {
        @UniqueConstraint(name = "uc_chatchannel_user_1_id", columnNames = {"user_1_id", "user_2_id"})
})
public class ChatChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_1_id", nullable = false)
    private User user1;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_2_id", nullable = false)
    private User user2;

}