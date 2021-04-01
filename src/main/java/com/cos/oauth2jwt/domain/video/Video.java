package com.cos.oauth2jwt.domain.video;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.oauth2jwt.util.JsonToListConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Video {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String vimeoFolderId;
   
   @Column(name = "contents", columnDefinition = "json")
   @Convert(converter = JsonToListConverter.class)
   private List<Map<String, Object>> contents = new ArrayList<>();
    
   @JsonIgnoreProperties({"video"})
   @OneToMany(mappedBy = "video", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)  // mappedBy : reply의 변수명
   @OrderBy("id desc")
   private List<VideoReply> vReplys;
   
    @CreationTimestamp
    private Timestamp createDate;   
    
    
}