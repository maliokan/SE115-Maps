public class WayFinder {
    public static Routes findShortestRoute(CountryMap map) {
        int cityCount = map.cityCount;
        boolean[] visited = new boolean[cityCount];
        int[] minTimes = new int[cityCount];
        int[] previousCity = new int[cityCount];


        for (int i = 0; i < cityCount; i++) {    //assign starting time values as maximum in all routes
            minTimes[i] = Integer.MAX_VALUE;
            previousCity[i] = -1;
        }

        int start = map.startCity;
        int end = map.endCity;
        minTimes[start] = 0;        //Starting point will start from 0

        for (int i=0; i<cityCount-1 ;i++) {
            int currentCountry = -1;
            int minimumTime = Integer.MAX_VALUE;        //find minimum time city
            for (int j=0; j<cityCount ;j++) {
                if (!(visited[j]) && (minTimes[j] < minimumTime)) {
                    currentCountry = j;
                    minimumTime = minTimes[j];
                }
            }

            if (currentCountry == -1) break;
            visited[currentCountry] = true;

            //süreleri güncelle
            for (int k=0; k<cityCount ;k++) {
                if (!visited[k] && map.travelTimes[currentCountry][k] > 0) {
                    int newTime = minTimes[currentCountry] + map.travelTimes[currentCountry][k];
                    if (newTime < minTimes[k]) {
                        minTimes[k] = newTime;
                        previousCity[k] = currentCountry;
                    }
                }
            }
        }

        // Rotayı geri inşa et
        City[] path = new City[cityCount];
        int pathLength = 0;
        int current = end;
        while (current != -1) {
            path[pathLength++] = map.cities[current];
            current = previousCity[current];
        }

        // Yolu tersine çevir
        City[] finalPath = new City[pathLength];
        for (int i = 0; i < pathLength; i++) {
            finalPath[i] = path[pathLength - 1 - i];
        }

        return new Routes(finalPath, minTimes[end]);
    }
}

class Routes {
    public City[] route;
    public int totalTime;

    public Routes(City[] route, int totalTime) {
        this.route = route;
        this.totalTime = totalTime;
    }
}