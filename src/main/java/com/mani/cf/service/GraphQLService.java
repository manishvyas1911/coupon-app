package com.mani.cf.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.mani.cf.constant.Category;
import com.mani.cf.model.Coupon;
import com.mani.cf.repository.CouponRepository;
import com.mani.cf.service.datafetcher.AddCouponDataFecher;
import com.mani.cf.service.datafetcher.AllCouponDataFecher;
import com.mani.cf.service.datafetcher.DeleteCouponDataFecher;
import com.mani.cf.service.datafetcher.GetCouponByIdDataFecher;
import com.mani.cf.service.datafetcher.UpdateCouponByIdDataFecher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {
	
	//@Autowired
	// CouponRepository couponRepository;

	// to add the graphqls file
	@Value("classpath:schema.graphqls")
	Resource resource; 
	
	private GraphQL graphql;
	
	@Autowired
	private AllCouponDataFecher allCouponsDataFetcher;
	@Autowired
	private GetCouponByIdDataFecher getCouponDataFetcher;
	@Autowired
	private AddCouponDataFecher addCouponDataFetcher; 
	@Autowired
	private UpdateCouponByIdDataFecher updateCouponDataFetcher;
	@Autowired
	private DeleteCouponDataFecher deleteCouponDataFetcher;

	
	@PostConstruct
	private void loadSchema() throws IOException {
		// loadDummyData();
		
		File schemaFile = resource.getFile();
		TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
		graphql =GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildWiring() {
		// TODO Auto-generated method stub
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring ->  
					 typeWiring.dataFetcher("getCoupons", allCouponsDataFetcher)
							   .dataFetcher("getCouponById",getCouponDataFetcher)
					)
				.type("Mutation", typeWiring ->
				      typeWiring.dataFetcher("newCoupon", addCouponDataFetcher)
				     			.dataFetcher("deleteCoupon", deleteCouponDataFetcher)
				     			.dataFetcher("updateCoupon", updateCouponDataFetcher)
				     
				).build();
				
	}
	
	public GraphQL getGraphQL() {
		return graphql;
	}
	
	/*
	 * private void loadDummyData() { Stream.of(new Coupon( "Amazon10",
	 * "Get Flat 10 Rs", Category.AMAZON, 10), new Coupon("Flipkart10",
	 * "Get Flat 10 Rs", Category.FLIPKART, 10), new Coupon("Snap10",
	 * "Get Flat 10 Rs", Category.SNAPDEAL, 10), new Coupon("Uber10",
	 * "Get Flat 10 Rs", Category.UBER, 10), new Coupon("Zomato10",
	 * "Get Flat 10 Rs", Category.ZOMATO, 10), new Coupon("Amazon50",
	 * "Get Flat 50 Rs", Category.AMAZON, 50)) .forEach(coupon -> {
	 * couponRepository.save(coupon); }); }
	 */
}
