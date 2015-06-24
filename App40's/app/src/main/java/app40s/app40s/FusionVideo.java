package app40s.app40s;

import android.graphics.Movie;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.Channels;

/**
 * Created by ThomasD on 24/06/15.
 */
public class FusionVideo {
    MovieCreator mc = new MovieCreator();
    Movie video = mc.build(Channels.newChannel(AppendExample.class.getResourceAsStream("/count-video.mp4")));
    Movie audio = mc.build(Channels.newChannel(AppendExample.class.getResourceAsStream("/count-english-audio.mp4")));


    List<Track> videoTracks = video.getTracks();
    video.setTracks(new LinkedList<Track>());

    List<Track> audioTracks = audio.getTracks();


    for (Track videoTrack : videoTracks) {
        video.addTrack(new AppendTrack(videoTrack, videoTrack));
    }
    for (Track audioTrack : audioTracks) {
        video.addTrack(new AppendTrack(audioTrack, audioTrack));
    }

    IsoFile out = new DefaultMp4Builder().build(video);
    FileOutputStream fos = new FileOutputStream(new File(String.format("output.mp4")));
    out.getBox(fos.getChannel());
    fos.close();
}
