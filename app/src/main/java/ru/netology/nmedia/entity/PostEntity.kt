package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val authorAvatar: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    val likes: Int = 0,
    val viewed: Boolean,
    val saved: Boolean,
) {
    fun toDto() = Post(id, author,authorAvatar, content, published, likedByMe, likes,saved,viewed)

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(dto.id, dto.author,dto.authorAvatar, dto.content, dto.published, dto.likedByMe, dto.likes,dto.viewed,dto.saved,)

    }
}
fun List<PostEntity>.toDto(): List<Post> = map(PostEntity::toDto)
fun List<Post>.toEntity(): List<PostEntity> = map(PostEntity::fromDto)

