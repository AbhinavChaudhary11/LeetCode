import java.util.*;

class MovieRentingSystem {
    class Movie implements Comparable<Movie> {
        int shop, movie, price;
        Movie(int s, int m, int p) {
            shop = s; movie = m; price = p;
        }
        public int compareTo(Movie o) {
            if (price != o.price) return price - o.price;
            if (shop != o.shop) return shop - o.shop;
            return movie - o.movie;
        }
    }

    Map<Integer, TreeSet<Movie>> available;
    Map<List<Integer>, Movie> rentedMovies;
    TreeSet<Movie> rented;

    public MovieRentingSystem(int n, int[][] entries) {
        available = new HashMap<>();
        rentedMovies = new HashMap<>();
        rented = new TreeSet<>((a, b) -> {
            if (a.price != b.price) return a.price - b.price;
            if (a.shop != b.shop) return a.shop - b.shop;
            return a.movie - b.movie;
        });
        for (int[] e : entries) {
            Movie m = new Movie(e[0], e[1], e[2]);
            available.computeIfAbsent(e[1], k -> new TreeSet<>()).add(m);
            rentedMovies.put(Arrays.asList(e[0], e[1]), m);
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if (!available.containsKey(movie)) return res;
        Iterator<Movie> it = available.get(movie).iterator();
        int k = 5;
        while (it.hasNext() && k-- > 0) res.add(it.next().shop);
        return res;
    }

    public void rent(int shop, int movie) {
        Movie m = rentedMovies.get(Arrays.asList(shop, movie));
        available.get(movie).remove(m);
        rented.add(m);
    }

    public void drop(int shop, int movie) {
        Movie m = rentedMovies.get(Arrays.asList(shop, movie));
        rented.remove(m);
        available.get(movie).add(m);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        Iterator<Movie> it = rented.iterator();
        int k = 5;
        while (it.hasNext() && k-- > 0) {
            Movie m = it.next();
            res.add(Arrays.asList(m.shop, m.movie));
        }
        return res;
    }
}
