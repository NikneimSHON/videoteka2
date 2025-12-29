package converter;

import entity.enums.VideoQuality;
import jakarta.persistence.AttributeConverter;

public class VideoQualityConverter implements AttributeConverter<VideoQuality, String> {
    @Override
    public String convertToDatabaseColumn(VideoQuality attribute) {
        return attribute == null ? null : attribute.getLabel();
    }

    @Override
    public VideoQuality convertToEntityAttribute(String dbData) {
        return dbData == null ? null : VideoQuality.fromLine(dbData);
    }
}
