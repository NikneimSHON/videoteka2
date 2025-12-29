package entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VideoQuality {
    P360("360p"), P480("480p"), P720("720p"), P1080("1080p"), P4K("4К");

    private final String label;

    public static VideoQuality fromLine(String line) {
        for (VideoQuality quality : values()) {
            if (quality.label.equalsIgnoreCase(line)) {
                return quality;
            }
        }
        throw new IllegalArgumentException("Not find " + line);
    }

}
