package ua.karatnyk.service.utilities;

import java.util.List;
import java.util.stream.Collectors;

import ua.karatnyk.entity.Lesson;
import ua.karatnyk.entity.TheoreticalTask;
import ua.karatnyk.entity.VideoYouTubeTask;

public class Utilities {
	
	public static String parseLinkYouTube(String link) {
		if(link == null)
			return null;
		try {
			int begin = link.indexOf("=")+1;
			int end = link.indexOf("&");
			if(end == -1)
				return link.substring(begin);
			return link.substring(begin, end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static List<TheoreticalTask> applyFilterT(List<TheoreticalTask> theoreticalTasks, String filter) {
		return theoreticalTasks.stream().filter(p -> p.getTitle().contains(filter)).collect(Collectors.toList());
	}
	
	public static List<VideoYouTubeTask> applyFilterV(List<VideoYouTubeTask> videoYouTubeTasks, String filter) {
		return videoYouTubeTasks.stream().filter(p -> p.getTitle().contains(filter)).collect(Collectors.toList());
	}
	
	public static String nextNumberLesson(List<Lesson> list) {
		try {
			int result = list.stream().mapToInt(p -> Integer.parseInt(p.getNumber())).max().getAsInt()+1;
			return ""+result;
		} catch (Exception e) {
			return "";
		}
	}

}
