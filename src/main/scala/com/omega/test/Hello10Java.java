package com.omega.test;

public class Hello10Java {
	public static interface MovieGenre {
		public String describeABit();
	}
	
	public static class ActionFlick implements MovieGenre {
		
		@Override
		public String describeABit() {
			return "guns firing, fights, wild chases etc.";
		}
	}
	
	public static class Animation implements MovieGenre {
		
		@Override
		public String describeABit() {
			return "cartoons! What else?";
		}
	}
	
	public static class RomanticMovie implements MovieGenre {
		
		@Override
		public String describeABit() {
			return "some romantic flick";
		}
	}
	
	public static abstract class FamilyMember {
		private final String name;
		private final MovieGenre movieGenre;
		
		public FamilyMember(String name, MovieGenre movieGenre) {
			this.name = name;
			this.movieGenre = movieGenre;
		}

		public String getName() {
			return name;
		}

		public MovieGenre getMovieGenre() {
			return movieGenre;
		}
		
		public void letUsGoForAMovie() {
			System.out.println(getName() + " would love to watch " + movieGenre.describeABit());
		}
	}
	
	public static class Dad extends FamilyMember {
		
		public Dad() {
			super("Dad", new ActionFlick());
		}
	}
	
	public static class Mom extends FamilyMember {
		
		public Mom() {
			super("Mom", new RomanticMovie());
		}
	}
	
	public static class Kid extends FamilyMember {
		
		public Kid() {
			super("Kid", new Animation());
		}
	}
	
	public static void main(String[] args) {
		FamilyMember[] allInTheFamily = new FamilyMember[] { new Dad(), new Mom(), new Kid() };
		
		for (FamilyMember familyMember : allInTheFamily) {
			familyMember.letUsGoForAMovie();
		}
	}
}
