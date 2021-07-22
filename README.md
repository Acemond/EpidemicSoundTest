# Track Tracker

This is a test project that retrieves a set of tracks from Spotify's API
to track their popularity over time.

The main class is `TracksTracker`. It runs a method periodically (every day)
to retrieve and store Epidemic Sound tracks from Spotify API.

The job stores tracks in a MySQL database, you can change the url to
the database of your choice in the `application.properties` located
in the resources' folder.

In order to get the popularity over time, you can execute an SQL query like:

```sql
SELECT *
FROM popularity
WHERE trackId = '1Ku0J6YIKWOd6pZi4VlFLb'
  AND atDate > 2021-07-21
ORDER BY atDate DESC; 
```
