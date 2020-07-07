package sikuliX;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import utils.MiscUtils;

public class SikuliXFinders {
	
	//flag for highlighting matches
	//For whatever reason, highlights can block clicks so use cautiously
	final static boolean HIGHLIGHT_MATCHES = false;
	final static String HIGHLIGHT_COLOR = "red";
	final static double MIN_SCORE = 0.85;
	
	public static Match findAndClickRegionByImage(String imageName, Region region) {
		Match match = new Match();
		try {
			match = region.find(SikuliXFileDirectories.getImagesFolderPath()+"\\"+imageName);
			region.click(match);
			if(HIGHLIGHT_MATCHES) {
				match.highlight(HIGHLIGHT_COLOR);
			}
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		return match;
	}

	public static Match findAndClickRegionByText(String text, Region region) {
		Match match = new Match();
		try {
			match = region.findText(text);
			region.click(match);
			if(HIGHLIGHT_MATCHES) {
				match.highlight(HIGHLIGHT_COLOR);
			}
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		
		return match;
	}

	public static Match findTextWithOffset(String text, Region region, Point offset) {
		Match match = new Match();
		try {
			match = region.findText(text);
			if(HIGHLIGHT_MATCHES) {
				match.highlight(HIGHLIGHT_COLOR);
			}
			region.mouseMove(new Location(match.x+offset.x,match.y+offset.y));
		}
		catch (FindFailed e) {
			e.printStackTrace();
		}
		return match;
	}

	public static List<Match> findAllMatchesforPattern(String patternImage, Region region){
		Pattern pattern = new Pattern(SikuliXFileDirectories.getImagesFolderPath()+"\\"+patternImage);
		List <Match> matches = new ArrayList<Match>();
		try {
			Iterator<Match> matchesFound = region.findAll(pattern);
			matches = MiscUtils.iteratorToList(matchesFound);
		} catch (FindFailed e) {
			System.out.println("No matches were found for pattern. Returning empty list.");
			e.printStackTrace();
		}
		return matches;
	}

	public static Match findFirstMatch(String patternImage, Region region) {
		List<Match> matches = findAllMatchesforPattern(patternImage, region);
		Match closestMatch = new Match();
		if(!matches.isEmpty()) {
			closestMatch = matches.get(0);
			for(Match match:matches) {
				if(match.getScore()>MIN_SCORE) {
					int currentMatchDistanceFromTop = match.y-region.y;
					int closestMatchDistanceFromTop = closestMatch.y-region.y;
					if(currentMatchDistanceFromTop<closestMatchDistanceFromTop) {
						closestMatch = match;
					}
				}
			}
		}
		else {
			System.out.println("No matches for given pattern was found. Returning default match object.");
		}
		if(HIGHLIGHT_MATCHES) {
			closestMatch.highlight(HIGHLIGHT_COLOR);
		}
		return closestMatch;
	}

	public static Match findClosestPatternToRegion(Region region, String imageName, Region screenOrAppRegion) {
		Match closestMatch = new Match();
		List<Match> matches = findAllMatchesforPattern(imageName, screenOrAppRegion);
		Location regionLocation = region.getCenter();
		for(int i=0;i<matches.size();i++) {
			if(i==0) {
				closestMatch = matches.get(i);
			}
			else {
				int distanceFound = SikuliXUtils.getDistanceBetweenLocations(regionLocation, matches.get(i).getCenter());
				int closestDistance = SikuliXUtils.getDistanceBetweenLocations(regionLocation, closestMatch.getCenter());
				if(distanceFound<closestDistance) {
					closestMatch=matches.get(i);
				}
			}
		}
		if(HIGHLIGHT_MATCHES) {
			closestMatch.highlight(HIGHLIGHT_COLOR);
		}
		return closestMatch;
	}

}
