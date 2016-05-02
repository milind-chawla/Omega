package com.omega.zhello;

public class Hello8Java {
	public static abstract class FamilyMember {
		protected final FamilyMember next;
		
		public FamilyMember(final FamilyMember next) {
			this.next = next;
		}
		
		public abstract boolean canHandle(Task task);
		public abstract void handleIt(Task task);
		
		public void doSomeWork(Task task) {
			if(canHandle(task)) {
				handleIt(task);
			} else if(next != null) {
				next.doSomeWork(task);
			}
		}
	}
	
	public static class Task {
		private final String name;
		
		public String getName() {
			return name;
		}
		
		public Task(String name) {
			this.name = name;
		}
		
		private boolean containsOneOf(String... phraseList) {
			for(String phrase: phraseList) {
				if(contains(phrase)) {
					return true;
				}
			}
			
			return false;
		}
		
		private boolean contains(String phrase) {
			return name.toLowerCase().contains(phrase);
		}
		
		public boolean needsHardLabour() {
			return containsOneOf("wood", "Fell", "hunt");
		}
		
		public boolean needsHouseHoldSkills() {
			return containsOneOf("sew", "cook");
		}
		
		public boolean isLightWeight() {
			return containsOneOf("dog", "cat", "playground");
		}
	}
	
	public static class Dad extends FamilyMember {
		
		public Dad(FamilyMember next) {
			super(next);
		}

		@Override
		public boolean canHandle(Task task) {
			return task.needsHardLabour();
		}

		@Override
		public void handleIt(Task task) {
			System.out.println("Dad Handling: " + task.getName());
		}
	}
	
	public static class Mom extends FamilyMember {
		
		public Mom(FamilyMember next) {
			super(next);
		}

		@Override
		public boolean canHandle(Task task) {
			return task.needsHouseHoldSkills();
		}

		@Override
		public void handleIt(Task task) {
			System.out.println("Mom Handling: " + task.getName());
		}
	}
	
	public static void main(String[] args) {
		
	}
}
