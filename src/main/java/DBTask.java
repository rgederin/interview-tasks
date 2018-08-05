
public class DBTask {
    /**
     * We have list of movies. Each movie has title and year of release.
     Also we have list of reviewers. Each reviewer has a name.
     Reviewer could asses each movie with number of stars (1-5). Also each review has date when review was made.

     Each movie could have multiple reviewers. Each reviewer could reviwes multiple movies.

     Task: build a DB schema for this scenario.

     Write two queries:

     1. Find movies titles which are ranked with 3 and more stars. Result of the query should not contains duplicates:

             select distinct movie.title
             from `movie` join `rating`
             on movie.mID = rating.mID
             where rating.stars >= 3

     2. Find movies titles and reviewers names for movies which are ranked with 3 and more stars and sorted by movie title.

             select movie.title, reviewer.name
             from (`movie` join `rating` on movie.mID = rating.mID) join `reviewer` on rating.rID = reviewer.rID
             where rating.stars >= 3
             order by movie.title

     */
}
