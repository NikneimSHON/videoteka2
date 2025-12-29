package entity;

import entity.enums.VideoStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"videoMetadata", "videoCategory"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"videoMetadata", "videoCategory"})
@Builder
@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private Integer lenSeconds;
    private String description;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp()
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    private VideoStatus status;

    @Builder.Default
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VideoMetadata> videoMetadata = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VideoCategory> videoCategory = new ArrayList<>();

    public void addMetadata(VideoMetadata metadata) {
        videoMetadata.add(metadata);
        metadata.setVideo(this);
    }

    public void removeMetadata(VideoMetadata metadata) {
        videoMetadata.remove(metadata);
        metadata.setVideo(null);
    }

}
