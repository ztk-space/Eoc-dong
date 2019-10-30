package com.rk.mynewdome.bean;

public class VideoInfo {
    public int id;
    public String data;
    public long size;
    public String displayName;
    public String title;
    public long dateAdded;
    public long dateModified;
    public String mimeType;
    public long duration;
    public String artist;
    public String album;
    public String resolution;
    public String description;
    public int isPrivate;
    public String tags;
    public String category;
    public double latitude;
    public double longitude;
    public int dateTaken;
    public int miniThumbMagic;
    public String bucketId;
    public String bucketDisplayName;
    public int bookmark;

    public String thumbnailData;
    public int kind;
    public long width;
    public long height;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public long getDateModified() {
        return dateModified;
    }

    public void setDateModified(long dateModified) {
        this.dateModified = dateModified;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(int isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(int dateTaken) {
        this.dateTaken = dateTaken;
    }

    public int getMiniThumbMagic() {
        return miniThumbMagic;
    }

    public void setMiniThumbMagic(int miniThumbMagic) {
        this.miniThumbMagic = miniThumbMagic;
    }

    public String getBucketId() {
        return bucketId;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    public String getBucketDisplayName() {
        return bucketDisplayName;
    }

    public void setBucketDisplayName(String bucketDisplayName) {
        this.bucketDisplayName = bucketDisplayName;
    }

    public int getBookmark() {
        return bookmark;
    }

    public void setBookmark(int bookmark) {
        this.bookmark = bookmark;
    }

    public String getThumbnailData() {
        return thumbnailData;
    }

    public void setThumbnailData(String thumbnailData) {
        this.thumbnailData = thumbnailData;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", size=" + size +
                ", displayName='" + displayName + '\'' +
                ", title='" + title + '\'' +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                ", mimeType='" + mimeType + '\'' +
                ", duration=" + duration +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", resolution='" + resolution + '\'' +
                ", description='" + description + '\'' +
                ", isPrivate=" + isPrivate +
                ", tags='" + tags + '\'' +
                ", category='" + category + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", dateTaken=" + dateTaken +
                ", miniThumbMagic=" + miniThumbMagic +
                ", bucketId='" + bucketId + '\'' +
                ", bucketDisplayName='" + bucketDisplayName + '\'' +
                ", bookmark=" + bookmark +
                ", thumbnailData='" + thumbnailData + '\'' +
                ", kind=" + kind +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
