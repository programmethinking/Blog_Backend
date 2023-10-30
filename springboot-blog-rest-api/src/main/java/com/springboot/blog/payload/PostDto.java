package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;
// dto:Data Transfer Object
//transfer data from client to server
//explanation:carries data between postman(client)-Controller-Service-DAO-DB.也就是client-server，postman后面都是server
//client does not need to know how the data is retrieved from the data source.
//然后entity转成DTO返回到client，传统的路径是DB返回到Entity再返回到client。，这里不用这个，因为前者可以hide implementation detials of jpa entity


@Data
@Schema(
        description = "PostDto Model Information"
)
public class PostDto  {

    private Long id;
    @Schema(
            description = "Blog Post Title"
    )
    //title not null and empty
    //title should have 2 characters
    @NotEmpty
    @Size(min=2,message = "post should at least 2 characters")
    private String title;
    @Schema(
            description = "Blog Post Description"
    )
    //description not null and empty
    //description should have 10 characters
    @NotEmpty
    @Size(min=10,message = "description should at least 10 characters")
    private String description;
    @Schema(
            description = "Blog Post Content"
    )
    //content not null and empty
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

    @Schema(
            description = "Blog Post Category"
    )
    private Long categoryId;
}
