import entity.Video;
import entity.VideoMetadata;
import entity.enums.MetadataStatus;
import entity.enums.UserStatus;
import entity.enums.Role;
import entity.User;
import entity.embedded.Birthday;
import entity.embedded.PersonalInfo;
import entity.enums.VideoQuality;
import entity.enums.VideoStatus;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.Instant;
import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        Video video;
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()
        ) {
            session.beginTransaction();
            /*Video video = Video.builder()
                    .createdAt(Instant.now())
                    .description("ds")
                    .name("ds")
                    .lenSeconds(10)
                    .status(VideoStatus.PUBLISHED)
                    .updatedAt(Instant.now())
                    .build();

            session.persist(video);

            VideoMetadata metadata = VideoMetadata.builder()
                    .url("https://example.com/adda")
                    .quality(VideoQuality.P360)
                    .status(MetadataStatus.READY)
                    .video(video)
                    .build();

            session.persist(metadata);

*/
            video = session.find(Video.class,11L);
            session.getTransaction().commit();
        }

    }
}
