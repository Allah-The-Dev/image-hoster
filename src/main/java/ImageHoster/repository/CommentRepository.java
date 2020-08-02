package ImageHoster.repository;

import org.springframework.stereotype.Repository;

import ImageHoster.model.Comment;

import java.util.List;

import javax.persistence.*;

@Repository
public class CommentRepository {
    
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

     public void addNewComment(Comment newComment) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

	public List<Comment> getCommentsByImageId(Integer imageId) {
        EntityManager em = emf.createEntityManager();
        String queryForCommentListByImageId = "SELECT i from Comment i where i.image.id=:imageId";
        TypedQuery<Comment> query = em.createQuery(queryForCommentListByImageId, Comment.class).setParameter("imageId", imageId);
        List<Comment> resultList = query.getResultList();

        return resultList;
	}

}