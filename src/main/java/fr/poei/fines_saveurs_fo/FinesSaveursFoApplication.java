package fr.poei.fines_saveurs_fo;

import fr.poei.fines_saveurs_fo.entity.*;
import fr.poei.fines_saveurs_fo.entity.role.Role;
import fr.poei.fines_saveurs_fo.repository.*;
import fr.poei.fines_saveurs_fo.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class FinesSaveursFoApplication implements CommandLineRunner {

    final CustomerRepository customerRepository;
    final AddressRepository addressRepository;
    final AddressCustomerRepository addressCustomerRepository;
    final CategoryRepository categoryRepository;
    final ProductRepository productRepository;
    final AdminRepository adminRepository;
    final CartRepository cartRepository;
    final CartProductRepository cartProductRepository;
    final OrderRepository orderRepository;


    public FinesSaveursFoApplication(CustomerRepository customerRepository, AddressRepository addressRepository, AddressCustomerRepository addressCustomerRepository, CategoryRepository categoryRepository, ProductRepository productRepository, AdminRepository adminRepository, CartRepository cartRepository, CartProductRepository cartProductRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.addressCustomerRepository = addressCustomerRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.adminRepository = adminRepository;
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
        this.orderRepository = orderRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(FinesSaveursFoApplication.class, args);
    }



    @Override

    public void run(String... args) throws Exception {

//        // Insertion de l'administrateur
//
//        Admin superAdmin = new Admin();
//        superAdmin.setEmail("super-admin@fines-saveurs.com");
//        superAdmin.setPassword("admin");
//        adminRepository.save(superAdmin);
//
//        // Insertion de 2 clients
//
//        Customer shaunCreamer = new Customer();
//        shaunCreamer.setFirstname("Shaun");
//        shaunCreamer.setLastname("Creamer");
//        shaunCreamer.setPassword("12345");
//        shaunCreamer.setEmail("shaun@creamer.com");
//        shaunCreamer.setId(customerRepository.save(shaunCreamer).getId());
//
//        Customer jeremyTaylor = new Customer();
//        jeremyTaylor.setFirstname("Jeremy");
//        jeremyTaylor.setLastname("Taylor");
//        jeremyTaylor.setPassword("12345");
//        jeremyTaylor.setEmail("jeremy@taylor.com");
//        jeremyTaylor.setId(customerRepository.save(jeremyTaylor).getId());
//
//        // Insertion des adresses du dernier client
//
//        Address destination = new Address();
//        destination.setStreetNumber("37");
//        destination.setStreet("Brookview Drive");
//        destination.setCity("Prairie View");
//        destination.setPostcode("TX77445");
//        destination.setId(addressRepository.save(destination).getId());
//        AddressCustomer destinationAddress = new AddressCustomer();
//        destinationAddress.setAddressCustomerId(new AddressCustomerId(jeremyTaylor, destination));
//        destinationAddress.setType("destination");
//        addressRepository.save(destination);
//        addressCustomerRepository.save(destinationAddress);
//
//        Address invoicing = new Address();
//        invoicing.setStreetNumber("37");
//        invoicing.setStreet("Brookview Drive");
//        invoicing.setCity("Prairie View");
//        invoicing.setPostcode("TX77445");
//        invoicing.setId(addressRepository.save(invoicing).getId());
//        AddressCustomer invoicingAddress = new AddressCustomer();
//        invoicingAddress.setAddressCustomerId(new AddressCustomerId(jeremyTaylor, invoicing));
//        invoicingAddress.setType("facturation");
//        addressRepository.save(invoicing);
//        addressCustomerRepository.save(invoicingAddress);
//
//        // Insertion de produits et catégories
//
//        Category boissons = new Category();
//        boissons.setName("Boissons torréfiées");
//        categoryRepository.save(boissons);
//
//        Category douceurs = new Category();
//        douceurs.setName("Douceurs");
//        categoryRepository.save(douceurs);
//
//        // Insertion de produits dans les catégories
//
//        Product macarons = new Product();
//        macarons.setName("Macarons");
//        macarons.setBrand("Pierre Hermé");
//        macarons.setReference("JKKFYJ");
//        macarons.setStock(30);
//        macarons.setDescription("Légèrement craquants et fondants au coeur");
//        macarons.setIngredient("Sucre, oeufs, sel, vanille, beurre, colorant naturel");
//        macarons.setConditioning("Boîte de 20 macarons");
//        macarons.setOrigin("France");
//        macarons.setPrice(BigDecimal.valueOf(40));
//        macarons.setCategory(douceurs);
//
//        Product pacamara = new Product();
//        pacamara.setName("Café Arabica Pacamara");
//        pacamara.setBrand("Terres de café");
//        pacamara.setReference("IUGIUG");
//        pacamara.setStock(40);
//        pacamara.setDescription("Profond et parfumé");
//        pacamara.setIngredient("Café torréfié 100%");
//        pacamara.setConditioning("Boîte en aluminium de 250 grammes");
//        pacamara.setOrigin("Salvador");
//        pacamara.setPrice(BigDecimal.valueOf(8));
//        pacamara.setCategory(boissons);
//
//        Product theBergamote = new Product();
//        theBergamote.setName("Thé noir à la bergamote");
//        theBergamote.setBrand("Palais des Thés");
//        theBergamote.setReference("JHFOIO");
//        theBergamote.setStock(20);
//        theBergamote.setDescription("Profond et parfumé");
//        theBergamote.setIngredient("Thé, fleurs de bergamote");
//        theBergamote.setConditioning("Boîte en aluminium de 200 grammes");
//        theBergamote.setOrigin("Chine");
//        theBergamote.setPrice(BigDecimal.valueOf(10));
//        theBergamote.setCategory(boissons);
//
//        douceurs.setProducts(Arrays.asList(macarons));
//        boissons.setProducts(Arrays.asList(pacamara, theBergamote));
//
//        productRepository.save(macarons);
//        productRepository.save(pacamara);
//        productRepository.save(theBergamote);
//
//        // Ajout de produits au panier
//
//        // 1 - Création du panier
//        Cart cart = new Cart();
//        cart.setCustomer(jeremyTaylor);
//        cart.setCreatedAt(LocalDateTime.now());
//        cartRepository.save(cart);
//        // 2 - Ajout de produits (lignes de commande)
//        CartProduct cartProduct1 = new CartProduct(); // Nouvelle ligne de commande
//        cartProduct1.setCart(cart);
//        cartProduct1.setProduct(theBergamote);
//        cartProduct1.setQuantity((byte) 2);
//        theBergamote.setStock(theBergamote.getStock() - 2);
//        cartProductRepository.save(cartProduct1);
//        CartProduct cartProduct2 = new CartProduct(); // Nouvelle ligne de commande
//        cartProduct2.setCart(cart);
//        cartProduct2.setProduct(macarons);
//        cartProduct2.setQuantity((byte) 1);
//        macarons.setStock(macarons.getStock() - 1);
//        cartProductRepository.save(cartProduct2);
//
//        // Commande
//
//        Order order = new Order();
//        order.setCreatedAt(LocalDateTime.now());
//        order.setPaidAt(LocalDateTime.now());
//        order.setCart(cart);
//        order.setCustomer(cart.getCustomer());
//        orderRepository.save(order);
    }
}
