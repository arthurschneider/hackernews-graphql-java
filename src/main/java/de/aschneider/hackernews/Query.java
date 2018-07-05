
package de.aschneider.hackernews;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

/**
 * @author aschneider
 * @since 05.07.2018
 */

public class Query implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public Query(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public List<Link> allLinks() {
        return linkRepository.getAllLinks();
    }
}
