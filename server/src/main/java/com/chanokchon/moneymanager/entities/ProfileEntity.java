package com.chanokchon.moneymanager.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "full_name", length = 150)
    private String fullName;

    @Column(name = "email", unique = true, length = 150)
    private String email;

    @Column(name = "password", length = 150)
    private String password;

    @Column(name = "profile_image_url", length = 500)
    private String profileImageUrl;

    @CreationTimestamp // ตั้งค่าเวลาให้อัตโนมัติตอนที่ Entity ถูก INSERT ครั้งแรก
    @Column(name = "created_at", updatable = false) // updatable: ฟิลด์นี้จะไม่ถูกนำไปใช้ในคำสั่ง UPDATE ของ SQL หลังจากบันทึกครั้งแรกแล้ว
    private LocalDateTime createdAt;

    @UpdateTimestamp // อัปเดตค่าเวลาให้อัตโนมัติทุกครั้งที่ Entity ถูก UPDATE
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "activition_token")
    private String activitionToken;

    @PrePersist // คือ annotation สำหรับกำหนดเมธอดให้ถูกเรียกอัตโนมัติก่อนที่ Entity จะถูกบันทึก (INSERT) ลงฐานข้อมูลครั้งแรก
    public void prePersist() {
        if (this.isActive == null) {
            this.isActive = false;
        }
    }

    // https://youtu.be/RtMezvuOKE0?si=HXHUTfs4wOSfyZ7I&t=2624
}
