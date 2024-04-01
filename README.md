# Fuzzy Tune

This is a recommendation project that uses fuzzy matching using [fuzzy-matcher](https://github.com/intuit/fuzzy-matcher) library
and two extra matching algorithms such as TDB.

## Team:
- Bianca Soliz
- Axel Ayala
- Sebastian Barra
- José Luis Terán

**[Trello - Dashboard](https://trello.com/b/zwolHksR/fuzzy-tune)**

## 1. Objective

Use fuzzy matching or approximate string matching is a technique that helps us identify 
two approximately similar texts to provide the following challenge.

- Given a user X, return the top 5 similar users based on their information.
- Generate recommendations for a user based on their preferences or similarity with other 
users or the items acquired/consumed by the user.

## 2. About the Data

This projects uses the following data:
[Top 10000 Songs on Spotify 1960-Now](https://www.kaggle.com/datasets/joebeachcapital/top-10000-spotify-songs-1960-now/data)

**About Dataset:**

The "Top 10000 Spotify Songs - ARIA and Billboard Charts" is a comprehensive collection of 10,000 of 
the most popular songs that have dominated the music scene from 1960 to the present day. 
This dataset was curated based on rankings from both the ARIA (Australian Recording Industry Association) and Billboard charts, ensuring a diverse representation of songs that have achieved immense commercial success and cultural significance.

**Data Information**

`track_name: Name of the Track.`

`artist_name(s): Name of the Artist.`

`album_name: Name of the Album by Artist.`

`album_artist_name(s): Name of the Album with the Artist Name.`

`album_release_date: Release Year of the album.`

`album_image_url: Image of the Album.`

`artist_genres: Genres of the artist.`

`disc_number: Number of discs.`

`track_number: Number of tracks.`

`track_duration(ms): Duration of the track in milliseconds.`

`explicit: The lyrics or content of a song or a music video contain one or more of the criteria which could be considered offensive or unsuitable for children.`

`popularity: The higher the value the more popular the song is.`

`danceability: Danceability describes how suitable a track is for dancing based on a combination of musical elements including tempo, rhythm stability, beat strength, and overall regularity. A value of 0.0 is least danceable and 1.0 is most danceable.`

`energy: Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and activity.`

`speechiness: Speechiness detects the presence of spoken words in a track. The more exclusively speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the attribute value. Values above 0.66 describe tracks that are probably made entirely of spoken words. Values between 0.33 and 0.66 describe tracks that may contain both music and speech, either in sections or layered, including such cases as rap music. Values below 0.33 most likely represent music and other non-speech-like tracks.`

`acousticness: A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents high confidence the track is acoustic.`

`instrumentalness: Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as instrumental in this context. Rap or spoken word tracks are clearly "vocal". The closer the instrumentalness value is to 1.0, the greater likelihood the track contains no vocal content. Values above 0.5 are intended to represent instrumental tracks, but confidence is higher as the value approaches 1.0.`

`liveness: Detects the presence of an audience in the recording. Higher liveness values represent an increased probability that the track was performed live. A value above 0.8 provides strong likelihood that the track is live.`

`valence: A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track. Tracks with high valence sound more positive (e.g. happy, cheerful, euphoric), while tracks with low valence sound more negative (e.g. sad, depressed, angry).`

`track_preview_url: Preview of the track.`

## 3. About the Project

- User Data: Data about the users. 
- Item Data: Data about the items, in this case songs of Spotify.

## 4. About the Implementation

- Use the pre-processing and tokenization that already exists in the library and make sure
   to implement new ones. (Use at least two that already exist and two new ones.)
- Implement one of the matching algorithms you mentioned in the previous task. (Compare
   the algorithm implemented with the one already in the library.)
  - Compare score and time complexity.
