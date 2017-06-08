package blog.services;

import blog.models.Post;
import blog.models.User;
import blog.repositories.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Primary
public class PostServiceJpaImpl implements PostService {
	
	@Autowired
	private PostRepository postRepo;
	
    private List<Post> posts = new ArrayList<Post>() {{
        add(new Post(1L, "First Post", "<p>Line #1.</p><p>Line #2</p>", null));
        add(new Post(2L, "Second Post", "Second post content:<ul><li>line 1</li><li>line 2</li></p>", new User(10L, "pesho10", "Peter Ivanov")));
        add(new Post(3L, "Post #3", "<p>The post number 3 nice</p>", new User(10L, "merry", null)));
        add(new Post(4L, "Forth Post", "<p>Not interesting post</p>", null));
        add(new Post(5L, "Post Number 5", "<p>Just posting</p>", null));
        add(new Post(6L, "Sixth Post", "<p>Another interesting post</p>", null));
    }};

    @Override
    public List<Post> findAll() {
    return this.postRepo.findAll();
    }

    @Override
    public List<Post> findLatest5() {
    return this.postRepo.findLatest5Posts(new PageRequest(0, 5));
    }
    @Override
    public Post findById(Long id) {
    return this.postRepo.findOne(id);
    }
    @Override
    public Post create(Post post) {
    return this.postRepo.save(post);
    }
    @Override
    public Post edit(Post post) {
    return this.postRepo.save(post);
    }
    @Override
    public void deleteById(Long id) {
    this.postRepo.delete(id);
    }
}
