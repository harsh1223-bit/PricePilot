import { useEffect, useState } from "react";
import axios from "../api/axios";

function Products() {

  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {

      const response = await axios.get(
        "/api/products"
      );

      setProducts(response.data);

    } catch (error) {

      console.error(error);

    }
  };

  return (
    <div className="min-h-screen bg-gray-100 p-10">

      <h1 className="text-4xl font-bold mb-8">
        Products
      </h1>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">

        {products.map((product) => (

          <div
            key={product.id}
            className="bg-white rounded-xl shadow-md p-6"
          >
            <h2 className="text-2xl font-semibold">
              {product.name}
            </h2>

            <p className="mt-2">
              Priority Score:
              {" "}
              {product.priorityScore}
            </p>

            <p>
              Essential:
              {" "}
              {product.essential
                ? "Yes"
                : "No"}
            </p>

          </div>

        ))}

      </div>

    </div>
  );
}

export default Products;