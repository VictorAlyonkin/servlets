package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.IsExistException;
import ru.netology.model.Post;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class PostRepository {
    private static final Long ID_0 = 0L;
    private Long postId = 0L;

    private final Map<Long, Post> posts = Collections.synchronizedMap(new TreeMap<>());

    public List<Post> all() {
        return posts.values().stream().toList();
    }

    public Optional<Post> getById(long id) {
        return Optional.of(posts.get(id));
    }

    public synchronized Post save(Post post) throws IsExistException {

        if (ID_0.equals(post.getId())) {
            post = new Post(++postId, post.getContent());
            posts.put(postId, post);
            return post;
        }
        if (posts.containsKey(post.getId())) {
            posts.put(post.getId(), post);
            return post;
        }
        throw new IsExistException("Для создания/изменения неправильно указан id");
    }

    public void removeById(long id) {
        posts.remove(id);
    }
}
