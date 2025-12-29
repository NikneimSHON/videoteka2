package entity;

import entity.enums.VideoStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, updatable = false)
    private String name;

    @Column(name = "len_seconds", nullable = false)
    private Integer lenSeconds;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp()
    private Instant updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private VideoStatus status;

    @Builder.Default
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoMetadata> videoMetadata = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
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
