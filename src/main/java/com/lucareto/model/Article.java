package com.lucareto.model;

import static com.lucareto.db.utils.Utils.generateUrn;

import java.util.Date;
import java.util.Objects;

public class Article implements BeanConverter<Article> {
    
    private static final String NID = "article";
    
    private String id;
    private String content;
    
    private String authorId;
    private String section;
    private Date createdDate;
    private Date updatedDate;
    private Status status;
    private boolean published;
    
    public Article() {};
    
    private Article(Builder builder) {
        if (Objects.nonNull(builder.id)) 
            id = builder.id;
        else id = generateUrn(NID);
        this.authorId = builder.authorId;
        this.section = builder.section;
        this.content = builder.content;
        this.createdDate = builder.createdDate;
        this.updatedDate = builder.updatedDate;
        this.status = builder.status;
        this.published = builder.published;
    }
    
    public static class Builder {
        private String id;
        private String content;
        private String authorId;
        private String section;
        private Date createdDate = new Date();
        private Date updatedDate = new Date();
        private Status status = Status.INQUESTTION;
        private boolean published = false;
        
        public Article build() {
            return new Article(this);
        }

        public Builder(final String authorId, final String section) {
            this.authorId = authorId;
            this.section = section;
        }
        
        public Builder(final String id, final String authorId, final String section) {
            this(authorId, section);
            this.id = id;
        }
        
        public Builder content(final String content) {
            this.content = content;
            return this;
        }
        
        public Builder updatedDate(final Date date) {
            this.updatedDate = date;
            return this;
        }
        
        public Builder status(final Status status) {
            this.status = status;
            return this;
        }
        
        public Builder published(final boolean published) {
            this.published = published;
            return this;
        }
        
    }
        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public Article copyPropertyTo(final Article other) {
        if(other.getId().equals(this.getId())) {
            if(Objects.nonNull(this.content))
                other.setContent(content);
            if(Objects.nonNull(this.section)) 
                other.setSection(section);
            if(Objects.nonNull(this.updatedDate)) 
                other.setUpdatedDate(updatedDate);
            if(Objects.nonNull(this.published))
                other.setPublished(published);
            if(Objects.nonNull(this.status))
                other.setStatus(status);
        }
        return other;
    }
}
