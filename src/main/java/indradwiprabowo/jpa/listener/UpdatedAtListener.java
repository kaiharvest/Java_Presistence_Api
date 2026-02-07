package indradwiprabowo.jpa.listener;

import indradwiprabowo.jpa.entity.UpdateAtAware;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class UpdatedAtListener {

    @PrePersist
    @PreUpdate
    public void setLastUpdatedAt(UpdateAtAware object) {
        object.setUpdatedAt(LocalDateTime.now());
    }

}
