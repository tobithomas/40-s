package app40s.app40s;

import android.graphics.Movie;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.List;

/**
 * Created by ThomasD on 24/06/15.
 */
public class MovieCreator {
    /**
     * Creates <code>Movie</code> object from a <code>FileChannel</code>.
     *
     * @param channel input channel
     * @return a representation of the movie
     * @throws IOException in case of I/O error during IsoFile creation
     */

    public static Movie build(FileChannel channel) throws IOException {
        return build((ReadableByteChannel) channel);
    }


    /**
     * Creates <code>Movie</code> object from a <code>ReadableByteChannel</code>.
     *
     * @param channel input channel
     * @return a representation of the movie
     * @throws IOException in case of I/O error during IsoFile creation
     * @deprecated use {@link MovieCreator#build(FileChannel)} for memory efficient movie representation
     */
    public static Movie build(ReadableByteChannel channel) throws IOException {
        IsoFile isoFile = new IsoFile(channel);
        Movie m = new Movie();
        List<TrackBox> trackBoxes = isoFile.getMovieBox().getBoxes(TrackBox.class);
        for (TrackBox trackBox : trackBoxes) {
            m.addTrack(new Mp4TrackImpl(trackBox));
        }
        return m;
    }
}
