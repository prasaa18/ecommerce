package com.prasad.ecommercespringboot.service.ServiceImpl;

import com.prasad.ecommercespringboot.Exception.ProductException;
import com.prasad.ecommercespringboot.Request.CreateProductRequest;
import com.prasad.ecommercespringboot.model.Category;
import com.prasad.ecommercespringboot.model.Product;
import com.prasad.ecommercespringboot.repository.CategoryRepository;
import com.prasad.ecommercespringboot.repository.ProductRepository;
import com.prasad.ecommercespringboot.service.ProductService;
import com.prasad.ecommercespringboot.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

     private ProductRepository productRepository;
     private UserService userService;
     private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;


    }

    @Override
    public Product createProduct(CreateProductRequest req) {
        Category topLevel = categoryRepository.findByName(req.getTopLevelCategory());

        if(topLevel==null){
            Category  topLevelCategory = new Category();
            topLevelCategory.setName(req.getTopLevelCategory());
            topLevelCategory.setLevel(1);
            topLevel =categoryRepository.save(topLevelCategory);
        }

        Category secondLevel=categoryRepository.findByNameAndParant(req.getSecondLevelCategory(),topLevel.getName());

        if(secondLevel==null) {

            Category secondLavelCategory=new Category();
            secondLavelCategory.setName(req.getSecondLevelCategory());
            secondLavelCategory.setParentCategory(topLevel);
            secondLavelCategory.setLevel(2);

            secondLevel= categoryRepository.save(secondLavelCategory);
        }

        Category thirdLevel=categoryRepository.findByNameAndParant(req.getThirdLevelCategory(),secondLevel.getName());

        if(thirdLevel==null) {

            Category thirdLavelCategory=new Category();
            thirdLavelCategory.setName(req.getThirdLevelCategory());
            thirdLavelCategory.setParentCategory(secondLevel);
            thirdLavelCategory.setLevel(3);

            thirdLevel=categoryRepository.save(thirdLavelCategory);
        }

     Product product = new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setImageUrl(req.getImageUrl());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setSizes(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct= productRepository.save(product);

        System.out.println("products - "+product);

        return savedProduct;


    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return  "Product deleted successfully";

    }

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductException {
        Product product = findProductById(productId);
        if(req.getQuantity()!=0){
            product.setQuantity(req.getQuantity());
        }
        return  productRepository.save(product);
    }

    @Override
    public Product findProductById(long id) throws ProductException {
        Optional<Product> product=productRepository.findById(id);
        if(product.isPresent()){
            return  product.get();
        }
        throw  new ProductException("Product not found with id -"+id);

    }

    @Override
    public List<Product> findProductByCategory(String category) {

        System.out.println("category --- "+category);

        List<Product> products = productRepository.findByCategory(category);

        return products;
    }
    @Override
    public List<Product> recentlyAddedProduct() {

        return productRepository.findTop10ByOrderByCreatedAtDesc();
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> color, List<String> sizes,
                                       Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock,
                                       Integer pageNumber, Integer pageSize) {

        Pageable pagebble = PageRequest.of(pageNumber,pageSize);

        List<Product> products = productRepository.filterProducts(category,minPrice,maxPrice,minDiscount,sort);

        if(!color.isEmpty()){
            products = products.stream().filter(p-> color.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor())))
                    .collect(Collectors.toList());
        }

        if(stock !=null){
            if(stock.equals("in_stock")){
                products =products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
            }
            else if (stock.equals("out_of_stock")){
                products =products.stream().filter((p->p.getQuantity()<1)).collect(Collectors.toList());
            }
        }

        int startIndex =(int) pagebble.getOffset();                                        //for page1//1 10
        int  endIndex= Math.min(startIndex+pagebble.getPageSize(), products.size());  // for page 2 - 11 --21   // 10 +11 =21 or 5

        List<Product> pageContent = products.subList(startIndex,endIndex);

        Page<Product> filteredProducts =new PageImpl<>(pageContent,pagebble,products.size());

        return  filteredProducts;
    }


    @Override
    public List<Product> searchProduct(String query) {
        List<Product> products=productRepository.searchProduct(query);
        return products;
    }
}
