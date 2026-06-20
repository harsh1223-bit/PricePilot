import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../api/axios";

function ShoppingLists() {

  const [lists, setLists] = useState([]);
  const [listName, setListName] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    fetchLists();
  }, []);

  const fetchLists = async () => {

    try {

      const response =
        await axios.get("/api/shopping-lists");

      setLists(response.data);

    } catch (error) {

      console.error(error);

    }
  };

  const createList = async () => {

    if (!listName.trim()) return;

    try {

      await axios.post(
        "/api/shopping-lists",
        {
          name: listName
        }
      );

      setListName("");

      fetchLists();

    } catch (error) {

      console.error(error);

    }
  };

  return (
    <div className="min-h-screen bg-gray-100 p-10">

      <h1 className="text-4xl font-bold mb-8">
        Shopping Lists
      </h1>

      <div className="bg-white p-6 rounded-xl shadow mb-8">

        <input
          type="text"
          placeholder="List Name"
          value={listName}
          onChange={(e) =>
            setListName(e.target.value)
          }
          className="
            border
            p-2
            rounded
            mr-4
          "
        />

        <button
          onClick={createList}
          className="
            bg-blue-600
            text-white
            px-4
            py-2
            rounded
          "
        >
          Create List
        </button>

      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">

        {lists.map((list) => (

          <div
            key={list.id}
            className="
              bg-white
              rounded-xl
              shadow-md
              p-6
            "
          >
            <h2 className="text-2xl font-semibold">
              {list.name}
            </h2>

            <p className="mt-2">
              List ID: {list.id}
            </p>

            <button
              onClick={() =>
                navigate(
                  `/shopping-lists/${list.id}`
                )
              }
              className="
                bg-green-600
                text-white
                px-4
                py-2
                rounded
                mt-4
              "
            >
              View List
            </button>

          </div>

        ))}

      </div>

    </div>
  );
}

export default ShoppingLists;