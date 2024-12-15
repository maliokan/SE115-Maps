public class WayFinder {
    public static RouteResult findShortestRoute(CountryMap map) {
        int countryCount = map.countryCount;
        boolean[] visited = new boolean[countryCount];
        int[] minTimes = new int[countryCount];
        int[] previousCountry = new int[countryCount];

        // Tüm süreler başlangıçta sonsuz
        for (int i = 0; i < countryCount; i++) {
            minTimes[i] = Integer.MAX_VALUE;
            previousCountry[i] = -1;
        }

        int start = map.startCountry;
        int end = map.endCountry;
        minTimes[start] = 0;

        for (int count = 0; count < countryCount - 1; count++) {
            // En düşük süreli ülkeyi bul
            int currentCountry = -1;
            int minTime = Integer.MAX_VALUE;
            for (int i = 0; i < countryCount; i++) {
                if (!visited[i] && minTimes[i] < minTime) {
                    currentCountry = i;
                    minTime = minTimes[i];
                }
            }

            if (currentCountry == -1) break;
            visited[currentCountry] = true;

            // Komşu ülkelerin sürelerini güncelle
            for (int i = 0; i < countryCount; i++) {
                if (!visited[i] && map.travelTimes[currentCountry][i] > 0) {
                    int newTime = minTimes[currentCountry] + map.travelTimes[currentCountry][i];
                    if (newTime < minTimes[i]) {
                        minTimes[i] = newTime;
                        previousCountry[i] = currentCountry;
                    }
                }
            }
        }

        // Rotayı geri inşa et
        City[] path = new City[countryCount];
        int pathLength = 0;
        int current = end;
        while (current != -1) {
            path[pathLength++] = map.countries[current];
            current = previousCountry[current];
        }

        // Yolu tersine çevir
        City[] finalPath = new City[pathLength];
        for (int i = 0; i < pathLength; i++) {
            finalPath[i] = path[pathLength - 1 - i];
        }

        return new RouteResult(finalPath, minTimes[end]);
    }
}

class RouteResult {
    City[] route;
    int totalTime;

    public RouteResult(City[] route, int totalTime) {
        this.route = route;
        this.totalTime = totalTime;
    }
}