
package de.aschneider.hackernews;

import javax.servlet.annotation.WebServlet;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

/**
 * @author aschneider
 * @since 05.07.2018
 */

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndPoint extends SimpleGraphQLServlet {

    private static final long serialVersionUID = -5821578827438803103L;
    private static final LinkRepository linkRepository;

    static {
        //Change to `new MongoClient("mongodb://<host>:<port>/hackernews")`
        //if you don't have Mongo running locally on port 27017
        MongoDatabase mongo = new MongoClient().getDatabase("hackernews");
        linkRepository = new LinkRepository(mongo.getCollection("links"));
    }
    
    public GraphQLEndPoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("shema.graphqls")
                .resolvers(new Query(linkRepository), new Mutation(linkRepository))
                .build()
                .makeExecutableSchema();
    }
}
