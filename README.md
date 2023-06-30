# NCPF Specifications (VERY WIP)
*For Legacy NCPF versions, see [NCPF Format and Configuration Guidelines](https://docs.google.com/document/d/1dzU2arDrD7n9doRua8laxzRy9_RtX-cuv1sUJBB5aGY/edit#heading=h.a5zsimflpf1b)*

# File Format
NCPF is no longer just a file type, but rather a data structure, and can be saved in any file format that supports it.
Files should have a dual extension, with `.ncpf` followed by the extension of the format (For example, `.ncpf.json`)
Another prefix extension may be added as well to help denote the contents of the file; (ex. `.cfg.ncpf.json`) There is no defined naming convention for prefix extensions

Any file format that supports the neccesary data structure is supported.

# Primitive Types
- Object (Contains String-keyed elements in no particular order)
- Boolean
- String
- Integer (32 bit signed)
- Float (32 bit)
- List (An ordered list of elements, contains many items of one type)

# Complex Types
Commonly used types within NCPF that are standardized and defined here. They are all objects, containing the listed substrucutre

## Element
A part used in a design.
Elements have the following structure:
- **Object** `modules` (Optional; keyed list of additional modules)
- **String** `type`
- (additional values depending on the type, listed below)

Here is a list of all defined types:
- `legacy_block` (for 1.12 and below)
- `legacy_item` (for 1.12 and below)
- `legacy_fluid` (for 1.12 and below)
- `oredict` (for 1.12 and below; use tags for newer versions)
- `block` (1.13+)
- `item` (1.13+)
- `fluid` (1.13+)
- `block_tag`
- `item_tag`
- `fluid_tag`

Custom types are also allowed.
It is highly reccommended to identify custom types to avoid compatibility issues (For example, using a namespaced key), but it is not required.

### Legacy Block
A block with optional metadata or NBT (1.12 and below)
- **String** `name` (Must be a namespaced key; ex. `minecraft:stone`)
- **Integer** `metadata` (must only be included if the block has multiple states)
- **String** `nbt` (Optional; only for block entities. This is in SNBT format)

### Legacy Item
An item with optional metadata or NBT (1.12 and below)
- **String** `name`
- **Integer** `metadata` (must only be included if the item has variants)
- **String** `nbt` (Optional; This is in SNBT format)

### Legacy Fluid
A fluid (1.12 and below)
- **String** `name`

### Oredict
An collection of blocks or items defined by the oredict
- **String** `oredict`

### Block
A block with optional blockstates or NBT (1.13+)
- **String** `name` (Must be a namespaced key; ex. `minecraft:stone`)
- **Object** `blockstate` (optional, only include if a specific state or states are required)
This contains all required states, with the state name as the key, and the value as either an `Integer` or `String`
*Numerical values may not be saved as strings*
- **String** `nbt` (Optional; only for block entities.  This is in SNBT format)

### Item
An item with optional NBT (1.13+)
- **String** `name` (Must be a namespaced key; ex. `minecraft:stick`)
- **String** `nbt` (Optional; This is in SNBT format)

### Fluid
A fluid (1.13+)
- **String** `name` (Must be a namespaced key; ex. `minecraft:water`)

### Block Tag
A collection of blocks with optional blockstates or NBT (1.13+)
- **String** `name` (Must be a namespaced key, prefixed with `#`; ex. `#minecraft:fences`)
- **Object** `blockstate` (optional, only include if a specific state or states are required)
This contains all required states, with the state name as the key, and the value as either an `Integer` or `String`
*Numerical values may not be saved as strings*
- **String** `nbt` (Optional; only for block entities.  This is in SNBT format)

### Item Tag
A collection of items with optional NBT (1.13+)
- **String** `name` (Must be a namespaced key, prefixed with `#`; ex. `#minecraft:fences`)
- **String** `nbt` (Optional; This is in SNBT format)

### Fluid Tag
A collection of fluids (1.13+)
- **String** `name` (Must be a namespaced key, prefixed with `#`; ex. `#minecraft:water`)

## Module
Refers to a section of NCPF that may be added, extended, or replaced by addons or other software. These may be used to store additional information about configurations or designs that are not a part of core NCPF
It is reccommended to use a namespaced key to denote modules, but this is not required.

# NCPF Structure
**NCPF Version 1**

## Base Structure
The base object contains the following tags:
- **Integer** `version` (The NCPF format version)
- **Object** `configuration` (This defines the indexing of elements used for designs in the file)
- **List** `addons` (Optional; a list of addons)
- **List** `designs` (Optional; A list of contained designs)

## Configurations
The configuration object contains configurations for defined design types; keys are recommended to be namespaced keys, but this is not required.
The general configuration format is as follows:
- **Object** `modules` (Contains various NCPF modules that can store additional information for the configuration)

The following configurations are defined in NCPF:
`nuclearcraft:underhaul_sfr`
`nuclearcraft:overhaul_sfr`
`nuclearcraft:overhaul_msr`
`nuclearcraft:overhaul_turbine`

### Underhaul SFR
- **List** `blocks` (List of Elements)
- **List** `fuels` (List of Elements)

### Overhaul SFR
- **List** `blocks` (List of Elements)
- **List** `coolant_recipes` (List of Elements)

Each block element may also contain:
- **List** `recipes` (Optional; List of Elements)

### Overhaul MSR
- **List** `blocks` (List of Elements)

Each block element may also contain:
- **List** `recipes` (Optional; List of Elements)

### Overhaul Turbine
- **List** `blocks` (List of Elements)
- **List** `recipes` (List of Elements)

## Addons
Addons can add configurations, or add elements or modules to any part of any configuration; They have the following structure:
- **Object** `modules` (keyed list of Modules)
- **Object** `configuration` (configurations added by this addon)

Elements in an addon must match the path of where they are to be added in the main configuration. Overriding elements is *not* allowed.
The resolution for conflicting modules depends on the specific module

## Designs
A design is a multiblock, structure, or other object described by this file; they have the following structure:
- **String** `type` (the type of the design. This must match the name of a configuration)
- **Object** `modules` (keyed list of Modules)
- **Array** `design` (An array containing a list of indicies that define the design; the structure of this array depends on the design type)

Element indicies start with the `configuration`, then continue through any matching elements in order of each addon in `addons`
(All indicies start at `0`)

The following types are defined in NCPF:
`nuclearcraft:underhaul_sfr`
`nuclearcraft:overhaul_sfr`
`nuclearcraft:overhaul_msr`
`nuclearcraft:overhaul_turbine`

Multi-dimensional arrays must be consolidated into one array in the following order; (Here's the definition for 3 dimensions, but this applies to any number of dimensions)

Z ascending
Y ascending
X ascending
This is meant to be easy to parse with 3 FOR loops:
```js
for(x=0; x<xSize; x++){
    for(y=0; y<ySize; y++){
        for(z=0; z<zSize; z++){
            element[x][y][z] = nextIndex;
        }
    }
}
```

### Underhaul SFR
- **Array** `dimensions` (An array of 3 Integers; The exterior XYZ dimensions)
- **Array** `design` (A 3D array of block indicies defining each block in the reactor. (`-1` for air))
- **Integer** `fuel` (The index of the fuel used in the reactor)

### Overhaul SFR
- **Array** `dimensions` (An array of 3 Integers; The exterior XYZ dimensions)
- **Array** `design` (A 3D array of block indicies defining each block in the reactor. (`-1` for air))
- **Array** `block_recipes` (A 3D array of recipe indicies for each block in the reactor. These are in the same order as in `design`, but **Only the blocks that have recipes are included in this list** (`-1` for no recipe))
- **Integer** `coolant_recipe` (The index of the coolant recipe used in the reactor)

### Overhaul MSR
- **Array** `dimensions` (An array of 3 Integers; The exterior XYZ dimensions)
- **Array** `design` (A 3D array of block indicies defining each block in the reactor. (`-1` for air))
- **Array** `block_recipes` (A 3D array of recipe indicies for each block in the reactor. These are in the same order as in `design`, but **Only the blocks that have recipes are included in this list** (`-1` for no recipe))

### Overhaul Turbine
- **Array** `dimensions` (An array of 3 Integers; The exterior XYZ dimensions)
- **Array** `design` (A 3D array of block indicies defining each block in the turbine. (`-1` for air))
- **Integer** `recipe` (The index of the recipe used in the turbine)
