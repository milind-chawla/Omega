package com.omega.test;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class Hello7Java {
	public static abstract class Mom {
		protected abstract List<String> mumsOfficeTaskList();
		
		public String getHerDailyRoutine() {
			final List<String> commonMumTasks = Lists.newArrayList(wakesUpEarly(), cooksFoodForKids(), feedKidsInEvening());
			List<String> herOfficeTasks = mumsOfficeTaskList();
			return makeADailyRoutineMsg(commonMumTasks, herOfficeTasks);
		}
		
		private String makeADailyRoutineMsg(List<String> commonMumTasks, List<String> herOfficeTasks) {
			final List<String> allTasks = Lists.newArrayList("---Daily Routine For a mum---");
			
			allTasks.addAll(commonMumTasks);
			allTasks.addAll(formatOfficeRoutine(herOfficeTasks));
			allTasks.add("---Thats all a mum can do in a day!---");
			
			String allTasksMsgList = Joiner.on("\n").join(allTasks);
			
			return allTasksMsgList;
		}
		
		private List<String> formatOfficeRoutine(List<String> workMsg) {
			List<String> formattedMsgList = Lists.newArrayList("My office routine");
			
			for (final String msg : workMsg) {
				formattedMsgList.add("\t" + msg);
			}
			
			formattedMsgList.add("No more shop talk");
			
			return formattedMsgList;
		}
		
		private String feedKidsInEvening() {
			return "Feeding kids in the evening";
		}
		
		private String cooksFoodForKids() {
			return "Cooking Food for my kids";
		}
		
		private String wakesUpEarly() {
			return "Sprightly mum I am - waking up!!!";
		}
	}
	
	public static class DoctorMom extends Mom {
		
		@Override
		protected final List<String> mumsOfficeTaskList() {
			List<String> dailyOfficeTasks = Lists.newArrayList("Get to the hospital", "Talk To Patients", "Perform Sugeries");
			return dailyOfficeTasks;
		}
	}
	
	public static void main(String[] args) {
		Mom m = new DoctorMom();
		System.out.println(m.getHerDailyRoutine());
	}
}
