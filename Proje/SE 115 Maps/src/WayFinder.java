public class WayFinder {
    public static Routes findShortestRoute(CountryMap map) {
        int cityCount = map.cityCount;
        boolean[] visited = new boolean[cityCount];
        int[] minTimes = new int[cityCount];
        int[] previousCity = new int[cityCount];


        for (int i=0; i<cityCount ;i++) {    //assign starting time values as maximum in all routes
            minTimes[i] = Integer.MAX_VALUE;
            previousCity[i] = -1;

        }

        int startCity = map.startCity;
        int endCity = map.endCity;
        minTimes[startCity] = 0;        //Starting point will start from 0



        for (int i=0; i<cityCount-1 ;i++) {
            int currentCity = -1;
            int minimumTime = Integer.MAX_VALUE;        //find minimum time city
            for (int j=0; j<cityCount ;j++) {
                if (!(visited[j]) && (minTimes[j] < minimumTime)) {
                    currentCity = j;
                    minimumTime = minTimes[j];
                }
            }



            if (currentCity == -1) break;
            visited[currentCity] = true;


            for (int k=0; k<cityCount ;k++) {
                if (!visited[k] && map.travelTimes[currentCity][k] > 0) {
                    int newTime = minTimes[currentCity] + map.travelTimes[currentCity][k];
                    if (newTime < minTimes[k]) {
                        minTimes[k] = newTime;
                        previousCity[k] = currentCity;
                    }
                }
            }
        }



        City[] reversedRoad = new City[cityCount];
        int roadLength = 0;
        int currentCity = endCity;
        while (currentCity != -1) {
            reversedRoad[roadLength] = map.cities[currentCity];
            currentCity = previousCity[currentCity];
            roadLength++;
        }



        City[] finalRoad = new City[roadLength];
        for (int i=0; i<roadLength; i++) {
            finalRoad[i] = reversedRoad[roadLength-(i+1)];
        }

        return new Routes(finalRoad, minTimes[endCity]);
    }
}

