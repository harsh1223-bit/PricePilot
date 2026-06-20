import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "../api/axios";

function ShoppingListDetails() {

  const { id } = useParams();

  const [items, setItems] = useState([]);

  const [optimization, setOptimization] =
    useState(null);

  useEffect(() => {
    fetchItems();
  }, []);

  const fetchItems = async () => {

    try {

      const response =
        await axios.get(
          `/api/shopping-lists/${id}/items`
        );

      setItems(response.data);

    } catch (error) {

      console.error(error);

    }
  };

  const optimizeList = async () => {

    try {

      const response =
        await axios.get(
          `/api/optimize/list/${id}`
        );

      setOptimization(
        response.data
      );

    } catch (error) {

      console.error(error);

    }
  };

  return (
    <div className="min-h-screen bg-gray-100 p-10">

      <h1 className="text-4xl font-bold mb-8">
        Shopping List Details
      </h1>

      <button
        onClick={optimizeList}
        className="
          bg-blue-600
          text-white
          px-4
          py-2
          rounded
          mb-8
        "
      >
        Optimize Basket
      </button>

      {optimization && (

        <div
          className="
            bg-white
            p-6
            rounded-xl
            shadow
            mb-8
          "
        >
          <h2
            className="
              text-2xl
              font-bold
              mb-4
            "
          >
            Optimization Result
          </h2>

          <p>
            Total Cost:
            ₹
            {optimization.multiStoreCost}
          </p>

          <p>
            Savings:
            ₹
            {optimization.savings}
          </p>

        </div>

      )}

      <div className="grid gap-4">

        {items.map((item) => (

          <div
            key={item.productId}
            className="
              bg-white
              p-4
              rounded-xl
              shadow
            "
          >
            <h2 className="text-xl font-semibold">
              {item.productName}
            </h2>

            <p>
              Quantity:
              {" "}
              {item.quantity}
            </p>

          </div>

        ))}

      </div>

    </div>
  );
}

export default ShoppingListDetails;