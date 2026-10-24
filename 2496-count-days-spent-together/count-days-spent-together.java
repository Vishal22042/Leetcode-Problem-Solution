
class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice,
                                 String arriveBob, String leaveBob) {

        int[] daysInMonth = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };

        int aliceStart = convertToDays(arriveAlice, daysInMonth);
        int aliceEnd = convertToDays(leaveAlice, daysInMonth);
        int bobStart = convertToDays(arriveBob, daysInMonth);
        int bobEnd = convertToDays(leaveBob, daysInMonth);

        int start = Math.max(aliceStart, bobStart);
        int end = Math.min(aliceEnd, bobEnd);

        return Math.max(0, end - start + 1);
    }

    private int convertToDays(String date, int[] daysInMonth) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int totalDays = day;

        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonth[i];
        }

        return totalDays;
    }
}
