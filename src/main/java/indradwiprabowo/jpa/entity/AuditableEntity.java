package indradwiprabowo.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.time.LocalDateTime;

// saran untuk membuat Mapped Superclass diwajibkan menggunakan abstract class
@MappedSuperclass
public abstract class AuditableEntity<T extends Serializable> {

    @Id
    private T id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updateAd;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAd() {
        return updateAd;
    }

    public void setUpdateAd(LocalDateTime updateAd) {
        this.updateAd = updateAd;
    }
}
